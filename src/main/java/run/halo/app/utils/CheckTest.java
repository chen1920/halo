package run.halo.app.utils;

import javax.crypto.Cipher;
import java.util.Properties;

/**
 * Desc:
 * ------------------------------------
 * Author:chenlong39@meituan.com
 * Date:2021/7/12
 * Time:2:16 下午
 */
public class CheckTest {

    public static void main(String[] args) {

    }

    abstract class A {

        public void foo(Properties props) throws Exception {
            Cipher.getInstance("AES/ECB/NoPadding"); // Noncompliant {{Use Galois/Counter Mode (GCM/NoPadding) instead.}}
            Cipher.getInstance("AES/CBC/PKCS5Padding"); // Noncompliant
            Cipher.getInstance("AES/GCM/NoPadding"); // Compliant
            Cipher.getInstance("DES/ECB/NoPadding"); // Compliant, not AES
            Cipher.getInstance("AES/ECB/NoPadding", getProvider()); // Noncompliant
            Cipher.getInstance("AES/ECB/NoPadding", "someProvider"); // Noncompliant

            String algo = props.getProperty("myAlgo", "AES/ECB/PKCS5Padding");
            Cipher.getInstance(algo); // Noncompliant

            Cipher.getInstance("AES" + "/ECB/NoPadding"); // Noncompliant
            Cipher.getInstance(null);
            Cipher.getInstance("");
        }

        abstract java.security.Provider getProvider();

    }


}
