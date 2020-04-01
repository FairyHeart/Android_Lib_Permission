package com.lib.permission

/**
 * 动态申请的权限
 *
 * @author: GuaZi.
 * @date  : 2020-04-01.
 */
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.ANNOTATION_CLASS,
    AnnotationTarget.CONSTRUCTOR,
    AnnotationTarget.PROPERTY_SETTER,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.TYPEALIAS
)
@MustBeDocumented
annotation class Dynamic {
}