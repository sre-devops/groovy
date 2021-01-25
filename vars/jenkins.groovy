def call() {
    timestamps {
        ansiColor('xterm') {
            node('master') {
                stage('Git CheckOut') {
                    aqua("alpine")
                }
            }
        }
    }
}