import groovy.json.JsonSlurper

class GitHubClient {

    static List fetchAdminRepos() {
        def token = System.getenv("GITHUB_TOKEN")
        if (!token) {
            throw new IllegalStateException("GITHUB_TOKEN environment variable not set")
        }

        def repos = []
        def page = 1
        def perPage = 100

        while (true) {
            def url = "https://api.github.com/user/repos?per_page=$perPage&page=$page"

            def connection = new URL(url).openConnection()
            connection.setRequestProperty("Authorization", "Bearer $token")
            connection.setRequestProperty("Accept", "application/vnd.github+json")

            def json = new JsonSlurper().parse(connection.inputStream)

            if (json.isEmpty()) break

            // Only keep repos where you have admin rights
            repos.addAll(json.findAll { it.permissions?.admin })
            page++
        }

        return repos
    }
}
