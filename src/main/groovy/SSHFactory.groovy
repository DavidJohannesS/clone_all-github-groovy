import org.eclipse.jgit.transport.sshd.SshdSessionFactory
import org.eclipse.jgit.transport.sshd.SshdSessionFactoryBuilder

class SSHFactory {

    static void setup() {
        def home = new File(System.getProperty("user.home"))
        def sshDir = new File(home, ".ssh")

        def factory = new SshdSessionFactoryBuilder()
                .setHomeDirectory(home)
                .setSshDirectory(sshDir)
                .setPreferredAuthentications("publickey")
                .build()

        SshdSessionFactory.setInstance(factory)
    }
}
