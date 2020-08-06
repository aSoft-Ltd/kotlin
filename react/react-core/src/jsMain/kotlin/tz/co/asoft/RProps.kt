package tz.co.asoft

import react.RProps
import react.router.dom.RouteResultProps

val <T : RProps> T.withRouter get() = unsafeCast<RouteResultProps<T>>()

val <T : RProps> T.history get() = withRouter.history

val <T : RProps> T.location get() = withRouter.location

val <T : RProps> T.match get() = withRouter.match