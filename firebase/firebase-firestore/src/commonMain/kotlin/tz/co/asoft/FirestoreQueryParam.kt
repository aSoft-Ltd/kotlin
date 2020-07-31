package tz.co.asoft

import tz.co.asoft.FirestoreQueryParam.Operator

class FirestoreQueryParam(val left: String, val op: Operator, val right: Any) {
    enum class Operator {
        LessThan,
        LessThanOrEqual,
        Equals,
        GreaterThanOrEqual,
        GreaterThan,
        ArrayContains
    }
}

infix fun String.isLessThan(other: String) =
    FirestoreQueryParam(this, Operator.LessThan, other)

infix fun String.isLessThan(other: Number) =
    FirestoreQueryParam(this, Operator.LessThan, other)

infix fun String.isLessThanOrEqualTo(other: String) =
    FirestoreQueryParam(
        this,
        Operator.LessThanOrEqual,
        other
    )

infix fun String.isLessThanOrEqualTo(other: Number) =
    FirestoreQueryParam(
        this,
        Operator.LessThanOrEqual,
        other
    )

infix fun String.equals(other: String) =
    FirestoreQueryParam(this, Operator.Equals, other)

infix fun String.equals(other: Number) =
    FirestoreQueryParam(this, Operator.Equals, other)

infix fun String.equals(other: Boolean) =
    FirestoreQueryParam(this, Operator.Equals, other)

infix fun String.isGreaterThanOrEqualTo(other: String) =
    FirestoreQueryParam(
        this,
        Operator.GreaterThanOrEqual,
        other
    )

infix fun String.isGreaterThanOrEqualTo(other: Number) =
    FirestoreQueryParam(
        this,
        Operator.GreaterThanOrEqual,
        other
    )

infix fun String.isGreaterThan(other: String) =
    FirestoreQueryParam(this, Operator.GreaterThan, other)

infix fun String.isGreaterThan(other: Number) =
    FirestoreQueryParam(this, Operator.GreaterThan, other)

infix fun String.arrayContains(other: String) =
    FirestoreQueryParam(
        this,
        Operator.ArrayContains,
        other
    )

infix fun String.arrayContains(other: Number) =
    FirestoreQueryParam(
        this,
        Operator.ArrayContains,
        other
    )