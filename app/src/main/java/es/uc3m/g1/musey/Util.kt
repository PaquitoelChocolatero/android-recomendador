package es.uc3m.g1.musey

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

suspend fun <A, B> Iterable<A>.pmap(f: suspend (A) -> B): List<B> = runBlocking {
    map { async { f(it) } }.awaitAll()
}