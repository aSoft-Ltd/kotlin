package tz.co.asoft.geo.usecase.location.lastknown

import tz.co.asoft.geo.Cord

interface ILastKnownLocationUseCase {
    operator fun invoke() : Cord?
}