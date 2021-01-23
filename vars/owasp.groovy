def call() {
    try {
        dependencyCheck additionalArguments: '--scan . --format HTML --enableExperimental', odcInstallation: 'Dependency Checkers'
        dependencyCheckPublisher pattern: 'dependency-check-report.xml'
    } catch(Exception e) {
        println('[ERROR] The dependencyCheck does not work')
    }
}
