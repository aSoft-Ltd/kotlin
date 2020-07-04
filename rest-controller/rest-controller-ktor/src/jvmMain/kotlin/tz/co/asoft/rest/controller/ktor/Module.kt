package tz.co.asoft.rest.controller.ktor

import io.ktor.application.Application
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.request.receiveText
import io.ktor.routing.*
import io.ktor.util.pipeline.PipelineContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.list
import kotlinx.serialization.builtins.nullable
import kotlinx.serialization.builtins.serializer
import tz.co.asoft.persist.model.Entity
import tz.co.asoft.rest.controller.core.IRestController
import tz.co.asoft.result.Result
import tz.co.asoft.result.Result.Companion.RJson
import tz.co.asoft.result.Result.Success
import tz.co.asoft.result.asFailure

open class Module<T : Entity>(
    version: String,
    root: String,
    subRoot: String?,
    private val serializer: KSerializer<T>,
    private val controller: IRestController<T>
) : IModule {

    private val path = "/$version/$root" + if (subRoot != null) "/$subRoot" else ""

    private suspend fun PipelineContext<Unit, ApplicationCall>.getIdsFromBody(): List<String> {
        return RJson.parse(String.serializer().list, call.receiveText())
    }

    private suspend fun PipelineContext<Unit, ApplicationCall>.getEntitiesFromBody(): List<T> {
        return RJson.parse(serializer.list, call.receiveText())
    }

    override fun setRoutes(app: Application) = app.routing {
        post(path = path) {
            flow<Result<List<T>>> {
                emit(Success(controller.create(getEntitiesFromBody())))
            }.catch {
                emit(it.asFailure())
            }.collect {
                send(serializer.list, it)
            }
        }

        post("$path/load") {
            flow<Result<List<T>>> {
                emit(Success(controller.load(getIdsFromBody())))
            }.catch {
                emit(it.asFailure())
            }.collect {
                send(serializer.list, it)
            }
        }

        post("$path/wipe") {
            flow<Result<List<T>>> {
                emit(Success(controller.wipe(getEntitiesFromBody())))
            }.catch {
                emit(it.asFailure())
            }.collect {
                send(serializer.list, it)
            }
        }

        patch(path = path) {
            flow<Result<List<T>>> {
                emit(Success(controller.edit(getEntitiesFromBody())))
            }.catch {
                emit(it.asFailure())
            }.collect {
                send(serializer.list, it)
            }
        }

        get("$path/{id}") {
            flow<Result<T?>> {
                val id = call.parameters["id"] ?: throw Exception("Couldn't get id")
                emit(Success(controller.load(id)))
            }.catch {
                emit(it.asFailure())
            }.collect {
                send(serializer.nullable, it)
            }
        }

        delete(path = path) {
            flow<Result<List<T>>> {
                emit(Success(controller.delete(getEntitiesFromBody())))
            }.catch {
                emit(it.asFailure())
            }.collect {
                send(serializer.list, it)
            }
        }

        get("$path/paged/{pageNo}/{pageSize}") {
            val invalidPageNo = Exception("Invalid page number")
            val invalidPageSize = Exception("Invalid page size")
            flow<Result<List<T>>> {
                val pageNo = call.parameters["pageNo"]?.toInt() ?: throw invalidPageNo
                val pageSize = call.parameters["pageSize"]?.toInt() ?: throw invalidPageSize
                if (pageNo == 0) throw invalidPageNo
                if (pageSize == 0) throw invalidPageSize
                emit(Success(controller.paged(pageNo, pageSize)))
            }.catch {
                emit(it.asFailure())
            }.collect {
                send(serializer.list, it)
            }
        }

        get("$path/all") {
            flow<Result<List<T>>> {
                emit(Success(controller.all()))
            }.catch {
                emit(it.asFailure())
            }.collect {
                send(serializer.list, it)
            }
        }

        get("$path/all-deleted") {
            flow<Result<List<T>>> {
                emit(Success(controller.allDeleted()))
            }.catch {
                emit(it.asFailure())
            }.collect {
                send(serializer.list, it)
            }
        }
    }
}
