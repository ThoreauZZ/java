package com.thoreau.boot

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.InetAddress
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.atomic.AtomicLong


/**
 * 2018/9/3 下午11:15.
 * @author zhaozhou
 */
@RestController
class GreetingController {
    val counter = AtomicLong()
    var inetAddress = InetAddress.getLocalHost()
    @GetMapping("/greeting")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String) : Greeting{
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        val time = LocalDateTime.now().format(formatter);
        return Greeting(counter.incrementAndGet(),
                "Hello, $name", inetAddress.hostAddress
                , inetAddress.hostName
                , time)

    }

}