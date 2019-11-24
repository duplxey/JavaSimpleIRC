# SimpleJavaIRC
Simple IRC written in Java.

## Intellij IDEA SwingBuilder + Maven
Your program might throw NullPointerException if you used SwingBuilder and Maven at the same time.
A lot of solutions don't work, but the most simple one is to add com.intellij.forms_rt as a dependancy.