import SSHFactory
import org.eclipse.jgit.api.Git

class RepoCloner {

    static {
        SSHFactory.setup()
    }

    static void cloneRepo(String sshUrl, File targetDir) {
        if (targetDir.exists()) {
            println "Skipping ${targetDir} — already exists"
            return
        }

        println "Cloning ${sshUrl} → ${targetDir}"

        Git.cloneRepository()
            .setURI(sshUrl)
            .setDirectory(targetDir)
            .call()
    }
}
