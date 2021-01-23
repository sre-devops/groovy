def call() {
    timestamps {
        ansiColor('xterm') {
            node('master') {
                stage('Git CheckOut') {
                    git credentialsId: '2fc867d7-eaa8-45ff-95e7-3262148e4f23', url: 'https://github.com/fhause5/petlinic-fe-original.git'
                    sh 'npm install'
                    owasp()

                }
            }
        }
    }
}