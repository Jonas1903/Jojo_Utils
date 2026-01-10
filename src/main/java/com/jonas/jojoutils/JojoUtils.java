package com.jonas.jojoutils;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JojoUtils implements ModInitializer {
    public static final String MOD_ID = "jojoutils";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Jojo Utils initialized!");
    }
}
