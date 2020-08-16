package tz.co.asoft

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
import tz.co.asoft.Result.Success

open class RestModule<T : Entity>(
    override val version: String,
    override val root: String,
    override val subRoot: String?,
    private val serializer: KSerializer<T>,
    private val controller: IRestController<T>
) : IRestModule {
    private suspend fun PipelineContext<Unit, ApplicationCall>.getIdsFromBody(): List<String> {
        return RJson.parse(String.serializer().list, call.receiveText())
    }

    private suspend fun PipelineContext<Unit, ApplicationCall>.getEntitiesFromBody(): List<T> {
        return RJson.parse(serializer.list, call.receiveText())
    }

    override fun setRoutes(app: Application, log: Logger) = app.routing {
        post(path = path) {
            log.i("Creating entity at $path")
            flow<Result<List<T>>> {
                emit(Success(controller.create(getEntitiesFromBody())))
            }.catch {
                log.e("Failed to create entity at $path", it)
                emit(it.asFailure())
            }.collect {
                send(serializer.list, it)
            }
        }

        post("$path/load") {
            log.i("Loading entity at $path/load")
            flow<Result<List<T>>> {
                emit(Success(controller.load(getIdsFromBody())))
            }.catch {
                log.e("Failed to load entity at $path/load", it)
                emit(it.asFailure())
            }.collect {
                send(serializer.list, it)
            }
        }

        post("$path/wipe") {
            log.i("Wiping entities at $path/wipe")
            flow<Result<List<T>>> {
                emit(Success(controller.wipe(getEntitiesFromBody())))
            }.catch {
                log.e("Failed to wipe entity at $path/wipe")
                emit(it.asFailure())
            }.collect {
                send(serializer.list, it)
            }
        }

        patch(path = path) {
            log.i("Editing entity at $path")
            flow<Result<List<T>>> {
                emit(Success(controller.edit(getEntitiesFromBody())))
            }.catch {
                log.e("Failed to edit entity at $path", it)
                emit(it.asFailure())
            }.collect {
                send(serializer.list, it)
            }
        }

        get("$path/{uid}") {
            log.i("Loading entity at $path/{uid}")
            flow<Result<T?>> {
                val id = call.parameters["uid"] ?: throw Exception("Couldn't get uid")
                emit(Success(controller.load(id)))
            }.catch {
                log.e("Failed to load entity at $path/{uid}", it)
                emit(it.asFailure())
            }.collect {
                send(serializer.nullable, it)
            }
        }

        delete(path = path) {
            log.i("Deleting entity at $path")
            flow<Result<List<T>>> {
                emit(Success(controller.delete(getEntitiesFromBody())))
            }.catch {
                log.e("Failed to delete entity at $path", it)
                emit(it.asFailure())
            }.collect {
                send(serializer.list, it)
            }
        }

        get("$path/all") {
            log.i("Loading all entities at $path/all")
            flow<Result<List<T>>> {
                emit(Success(controller.all()))
            }.catch {
                log.e("Failed to load all entities at $path/all", it)
                emit(it.asFailure())
            }.collect {
                send(serializer.list, it)
            }
        }

        get("$path/all-deleted") {
            log.i("Loading all deleted entities at $path/all-deleted")
            flow<Result<List<T>>> {
                emit(Success(controller.allDeleted()))
            }.catch {
                log.e("Failed to load entities at $path/all-deleted", it)
                emit(it.asFailure())
            }.collect {
                send(serializer.list, it)
            }
        }
    }
}
