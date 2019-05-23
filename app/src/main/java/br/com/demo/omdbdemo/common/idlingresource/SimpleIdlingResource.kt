package br.com.demo.omdbdemo.common.idlingresource

import androidx.test.espresso.IdlingResource
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Source: https://github.com/googlesamples/android-testing/blob/master/ui/espresso/IdlingResourceSample/app/src/main/java/com/example/android/testing/espresso/IdlingResourceSample/IdlingResource/SimpleIdlingResource.java
 * */
class SimpleIdlingResource : IdlingResource {

    @Volatile
    private var callback: IdlingResource.ResourceCallback? = null

    private var isIdleNow = AtomicBoolean(false)

    override fun getName(): String {
        return this.javaClass.name
    }

    override fun isIdleNow(): Boolean {
        return isIdleNow.get()
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        this.callback = callback
    }

    /**
     * Sets the new idle state, if isIdleNow is true, it pings the [ResourceCallback].
     * @param isIdleNow false if there are pending operations, true if idle.
     */
    fun setIdleState(isIdleNow: Boolean) {
        this.isIdleNow.set(isIdleNow)
        if (isIdleNow) {
            callback?.onTransitionToIdle()
        }
    }
}