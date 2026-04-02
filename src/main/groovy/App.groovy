import GitHubClient
import RepoCloner

class App {
    static void main(String[] args) {
        if (args.length < 1) {
            println "Usage: java -jar github-cloneAll.jar <base-directory>"
            System.exit(1)
        }

        def baseDir = new File(args[0])
        if (!baseDir.exists()) baseDir.mkdirs()

        println "Fetching your GitHub repositories..."
        def repos = GitHubClient.fetchAdminRepos()

        println "Found ${repos.size()} admin-owned repositories"

        repos.each { repo ->
            def org = repo.full_name.split('/')[0]
            def name = repo.name

            def orgDir = new File(baseDir, org)
            if (!orgDir.exists()) orgDir.mkdirs()

            def targetDir = new File(orgDir, name)
            RepoCloner.cloneRepo(repo.ssh_url, targetDir)
        }

        println "Done."
    }
}
