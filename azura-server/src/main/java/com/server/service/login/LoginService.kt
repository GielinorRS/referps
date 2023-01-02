package com.server.service.login

import com.google.common.util.concurrent.ThreadFactoryBuilder
import com.ruse.net.PlayerSession
import com.ruse.net.packet.codec.PacketDecoder
import com.ruse.net.packet.codec.PacketEncoder
import com.ruse.net.security.IsaacRandom
import com.ruse.world.World
import com.server.service.Service
import java.util.concurrent.Executors
import java.util.concurrent.LinkedBlockingQueue
import java.util.logging.Logger

/**
 * TODO: add documentation
 *
 * @author  Stan van der Bend (https://www.rune-server.ee/members/StanDev/)
 * @since   24/11/2019
 * @version 1.0
 */
class LoginService: Service {

    private val logger: Logger = Logger.getLogger(LoginService::javaClass.name)
    val requests = LinkedBlockingQueue<LoginServiceRequest>()

    private var threadCount = 1

    override fun init() {
        val executorService = Executors
                .newFixedThreadPool(threadCount, ThreadFactoryBuilder()
                        .setNameFormat("login-worker")
                        .setUncaughtExceptionHandler { t, e -> logger.severe("Error with thread $t, ${e.message}") }.build())
        for (i in 0 until threadCount)
            executorService.execute(LoginWorker(this))
    }

    override fun postLoad() {
    }

    override fun bindNet() {}

    override fun terminate() {}

    fun addLoginRequest(msg: LoginServiceRequest) {
        requests.offer(msg)
    }

    fun successfulLogin(session: PlayerSession, encoderIsaac: IsaacRandom, decoderIsaac: IsaacRandom){

        val channel = session.channel!!
        val pipeline = channel.pipeline

        channel.attachment = session.player

        if(channel.isOpen){

            pipeline.replace("encoder", "encoder", PacketEncoder(encoderIsaac))
            pipeline.replace("decoder", "decoder", PacketDecoder(decoderIsaac))

            World.getLoginQueue().offer(session.player)
        }
    }

    /**
     * Indicates whether some other object is "equal to" this one. Implementations must fulfil the following
     * requirements:
     *
     * * Reflexive: for any non-null value `x`, `x.equals(x)` should return true.
     * * Symmetric: for any non-null values `x` and `y`, `x.equals(y)` should return true if and only if `y.equals(x)` returns true.
     * * Transitive:  for any non-null values `x`, `y`, and `z`, if `x.equals(y)` returns true and `y.equals(z)` returns true, then `x.equals(z)` should return true.
     * * Consistent:  for any non-null values `x` and `y`, multiple invocations of `x.equals(y)` consistently return true or consistently return false, provided no information used in `equals` comparisons on the objects is modified.
     * * Never equal to null: for any non-null value `x`, `x.equals(null)` should return false.
     *
     * Read more about [equality](https://kotlinlang.org/docs/reference/equality.html) in Kotlin.
     */
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    /**
     * Returns a hash code value for the object.  The general contract of `hashCode` is:
     *
     * * Whenever it is invoked on the same object more than once, the `hashCode` method must consistently return the same integer, provided no information used in `equals` comparisons on the object is modified.
     * * If two objects are equal according to the `equals()` method, then calling the `hashCode` method on each of the two objects must produce the same integer result.
     */
    override fun hashCode(): Int {
        return super.hashCode()
    }

    /**
     * Returns a string representation of the object.
     */
    override fun toString(): String {
        return super.toString()
    }
}