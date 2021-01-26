
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
                            "defaultValue": "nginx:latest, alpine:latest",
                            "description": "Comma separated user's emails. \nE.g.: ivan_ivanov@epam.com, john_doe@epam.com",
                            "name": "IMAGES"
                    )
            ])
    ])
    timestamps {
        ansiColor('xterm') {
            node('master') {
                stage('Git CheckOut') {
                    images = params.IMAGES
                    aqua(images)
                }
            }
        }
    }

}