timestamps {
    ansiColor('xterm') {
        node('master') {
            stage('Git CheckOut') {
                owasp()

            }
        }
    }
}