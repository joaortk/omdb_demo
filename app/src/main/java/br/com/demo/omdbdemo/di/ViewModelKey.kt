package br.com.demo.omdbdemo.di;

import androidx.lifecycle.ViewModel;
import dagger.MapKey;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;

import java.lang.annotation.*;
import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)
