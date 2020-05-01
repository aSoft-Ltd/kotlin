package tz.co.asoft.deployment.tools

import tz.co.asoft.deployment.target.Target

val Target.envTaskName get() = "environmentJson${name.capitalize()}"