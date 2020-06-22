# asoft-rx

A Kotlin multiplatform liblary for reactive kotlin. Now in Js and Jvm

---------
###Usage

<pre><code>
    val counts = Observable(0) // observable Int is infered by the compiler
    
    val observer1 = counts.observe { oldValue, newValue -> 
        println("counts (as observed by observer 1) changed from $oldValue to $newValue")
    }
    
    val observer2 = counts.observe { oldValue, newValue ->
        println("counts (as observed by observer 2) changed from $oldValue to $newValue")
    }
    
    counts.value = 4
    // observer 1 and observer 2 will be called
    
    counts.unObserve(observer1)
    
    counts.value = -4
    // only observer 2 will be called
    
    counts.unObserve(observer2)
    
    // no calling here. All observers are removed
</code></pre>