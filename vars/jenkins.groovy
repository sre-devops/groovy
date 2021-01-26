def call() {
    properties([
            buildDiscarder(
                    logRotator(
                            artifactDaysToKeepStr: '90',
                            artifactNumToKeepStr: '20',
                            daysToKeepStr: '90',
                            numToKeepStr: '20'
                    )
            ),
            parameters([
                    string(
                            "defaultValue": "",
                            "description": "Input Git url",
                            "name": "Git_url"
                    ),
                    string(
                            "defaultValue": "2fc867d7-eaa8-45ff-95e7-3262148e4f23",
                            "description": "Input Jenkins credentials",
                            "name": "Credentials"
                    ),
                    string(
                            "defaultValue": "nginx:latest, alpine:latest",
                            "description": "Comma separated images. \nE.g.: nginx:latest, alpine:latest",
                            "name": "IMAGES"
                    )
            ])
    ])
    timestamps {
        ansiColor('xterm') {
            node('master') {
                stage('OWASP security check') {
                    if (params.Git_url.isEmpty())
                        error("[ERROR] Git_url  can not be empty!")
                    git_url = params.Git_url
                    credentials = params.Credentials

                    println("[DEBUG] git_url: ${git_url}")
                    println("[DEBUG] credentials: ${credentials}")

                    git credentialsId: "${credentials}", url: "${git_url}"
                    sh 'npm install'
                    owasp()
                }
                stage('Aqua images security check') {
                    if (params.IMAGES.isEmpty())
                        error("[ERROR] IMAGES list can not be empty!")

                    images = params.IMAGES.replaceAll(" ", "").toLowerCase().split(',')
                    println("[DEBUG] images: ${images}")
                    aqua(images)
                }
            }
        }
    }
}