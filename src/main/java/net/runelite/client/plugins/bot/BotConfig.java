package net.runelite.client.plugins.bot;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigItem;

public interface BotConfig extends Config {
    @ConfigItem(
            keyName = "bot",
            name = "BadClient",
            description = "This client is bad.",
            position = 0
    )

    default boolean bot() {
        return true;
    }
}
