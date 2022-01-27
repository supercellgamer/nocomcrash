package me.x150.nocomcrash;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NoComCrash implements ModInitializer {
   public static Logger LOGGER = LogManager.getLogger();
   public static final String MOD_ID = "nocomcrash";
   public static final String MOD_NAME = "No comment crash";

   public void onInitialize() {
      log(Level.INFO, "Initializing");
   }

   public static void log(Level level, String message) {
      LOGGER.log(level, "[No comment crash] " + message);
   }
}
