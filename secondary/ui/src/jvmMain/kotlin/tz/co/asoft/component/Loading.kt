package tz.co.asoft.component

import tornadofx.label

class Loading : Component<Loading.Props, Any>() {
    class Props {
        var msg = "Loading"
    }

    override fun render() = label(props.msg)
}