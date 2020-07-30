package tz.co.asoft.enterprise.tables.reacttable

import kotlinx.css.Color
import kotlinx.css.RuleSet
import kotlinx.css.backgroundColor
import kotlinx.css.color
import styled.StyleSheet
import tz.co.asoft.Theme
import tz.co.asoft.dropdown_clazz

internal object styles : StyleSheet("themed-react-table") {
    private val follow_theme by css {
        backgroundColor = Color.transparent
        color = Color.inherit
    }

    fun table(theme: Theme): RuleSet = {
        color = Color.inherit
        child("div .rt-noData") {
            +follow_theme
        }

        child("div .pagination-bottom .-pagination .-center .-pageInfo .-pageJump input") {
            +follow_theme
        }

        child("div .pagination-bottom .-pagination .-center span select") {
            +follow_theme
            +theme.dropdown_clazz
        }
    }
}