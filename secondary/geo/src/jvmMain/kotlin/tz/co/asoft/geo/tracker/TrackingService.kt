package tz.co.asoft.geo.tracker

import tz.co.asoft.geo.Track
import tz.co.asoft.platform.core.Ctx

actual class TrackingService actual constructor(ctx: Ctx) : ITrackingServices {
    override var update_interval: Long
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}
    override var update_distance: Float
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}

    override fun setTrackListener(handler: (Track?) -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLocation(): Track? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun dispatch() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun stop() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}