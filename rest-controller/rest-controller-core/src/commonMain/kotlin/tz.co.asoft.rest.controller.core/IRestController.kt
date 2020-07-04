package tz.co.asoft.rest.controller.core

import tz.co.asoft.persist.model.Entity
import tz.co.asoft.persist.repo.IRepo

interface IRestController<T : Entity> : IRepo<T>