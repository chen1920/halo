package run.halo.app.utils;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * Desc:
 * ------------------------------------
 * Author:chenlong39@meituan.com
 * Date:2021/7/12
 * Time:3:20 下午
 */
public class Check2 {
    class NonSerializableWithoutConstructor {
    }

    class NonSerializableWithAccessibleNoArgConstructor {
        public NonSerializableWithAccessibleNoArgConstructor(String arg1) {
        }

        public NonSerializableWithAccessibleNoArgConstructor() {
        }
    }

    class NonSerializableWithoutAccessibleNoArgConstructor {
        int field;

        public NonSerializableWithoutAccessibleNoArgConstructor(String arg1) {
        }

        private NonSerializableWithoutAccessibleNoArgConstructor() {
        }
    }

    class A extends NonSerializableWithoutConstructor implements Serializable {
    }

    class B extends NonSerializableWithAccessibleNoArgConstructor implements Serializable {
    }

    class C1 extends NonSerializableWithoutAccessibleNoArgConstructor implements Serializable {
    } // Noncompliant [[sc=18;ec=66]] {{Add a no-arg constructor to "NonSerializableWithoutAccessibleNoArgConstructor".}}

    class C2 extends NonSerializableWithoutAccessibleNoArgConstructor implements Serializable { // Compliant
        Object writeReplace() throws ObjectStreamException {
            return null;
        }
    }

    class D implements Serializable {
    }

    class E extends NonSerializableWithoutAccessibleNoArgConstructor {
    }

    class F extends A {
    }

    class G {
        C1 c1 = new C1() {
            @Override
            public String toString() {
                return "";
            }

            Object writeReplace() throws ObjectStreamException {
                return null;
            }
        };
    }

    class Lab {
        void foo() {
            outer:
            for (int i = 0; i < 10; i++) {
                continue outer;
            }
            outer:
            for (int i = 0; i < 10; i++) {
                break outer;
            }
            label2:
            // Noncompliant [[sc=5;ec=11]] {{Remove this unused label.}}
            for (int i = 0; i < 10; i++) {
                break;
            }
        }

        void bar() {
            i:
            { // Compliant
                new Object() {
                    void qix() {
                        i:
                        break i;
                    }
                }.qix();
                break i;
            }
        }
    }

}
