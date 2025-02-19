package com.chrispy.plugins

import android.content.Context

import com.aliucord.Http
import com.aliucord.Utils
import com.aliucord.api.CommandsAPI
import com.aliucord.entities.Plugin
import com.aliucord.annotations.AliucordPlugin
import com.discord.api.commands.ApplicationCommandType

@AliucordPlugin
class LetMeGoogleThat : Plugin() {

    override fun start(ctx: Context) {
        val query = listOf(
                Utils.createCommandOption(
                        type = ApplicationCommandType.STRING,
                        name = "query",
                        description = "The query to search",
                        required = false,
                        default = false,
                        channelTypes = emptyList(),
                        choices = emptyList(),
                        subCommandOptions = emptyList(),
                        autocomplete = false
                )
        )
        commands.registerCommand(
                "LetMeGoogleThat",
                "Generates a google.it link",
                query
        )
        { ctx ->
            try {
                val url = Http.QueryBuilder("https://letmegooglethat.com/")
                        .append("q", ctx.getString("query"))
                        .toString()
                CommandsAPI.CommandResult(url)
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
                CommandsAPI.CommandResult("An error occured", null, false, "LetMeGoogleThat")
            }
        }
    }

    override fun stop(ctx: Context) = commands.unregisterAll()

}
