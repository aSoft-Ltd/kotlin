package tz.co.asoft.theme.default.typographies

import tz.co.asoft.theme.TextStyle
import tz.co.asoft.theme.Typography

actual val BasicTypography
    get() = Typography(
        h1 = TextStyle(2.0),
        h2 = TextStyle(1.5),
        h3 = TextStyle(1.17),
        h4 = TextStyle(1.0),
        h5 = TextStyle(0.83),
        h6 = TextStyle(0.67),
        subtitle1 = TextStyle(1.25),
        subtitle2 = TextStyle(1.15),
        body1 = TextStyle(1.0),
        body2 = TextStyle(1.0),
        button = TextStyle(1.0),
        caption = TextStyle(0.9),
        overline = TextStyle(0.9)
    )