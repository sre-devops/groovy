def call() {
    try {
        sh 'npm install || true'
        dependencyCheck additionalArguments: '--scan . --format XML --enableExperimental', odcInstallation: 'Dependency Checkers'
        dependencyCheckPublisher pattern: 'dependency-check-report.xml'
    } catch(Exception e) {
        println('[ERROR] The dependencyCheck does not work')
    }
}
