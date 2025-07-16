import jenkins.model.*
import org.jenkinsci.plugins.workflow.job.*
import hudson.plugins.git.*
import org.jenkinsci.plugins.workflow.job.properties.PipelineTriggersJobProperty
import com.cloudbees.jenkins.GitHubPushTrigger

def jenkins = Jenkins.instance

def jobName = "python-webhook-pipeline"

if (jenkins.getItem(jobName) == null) {
    println("Creating pipeline job: ${jobName}")

    def scm = new GitSCM("https://github.com/hitibash/jenkins-python-webhook.git")

    def flowDefinition = new org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition(
        scm,
        "Jenkinsfile"
    )

    def job = new WorkflowJob(jenkins, jobName)
    job.setDefinition(flowDefinition)

    // Add webhook trigger
    job.addProperty(new PipelineTriggersJobProperty(
        [new GitHubPushTrigger()]
    ))

    jenkins.add(job, jobName)
    job.save()

    println("Pipeline job created: ${jobName}")
} else {
    println("Job ${jobName} already exists.")
}
