def call() {
    try {
        sh "echo work"
    } catch(Exception e) {
        sh "echo does not work"
    }
}
