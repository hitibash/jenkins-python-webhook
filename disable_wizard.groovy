#!groovy

import jenkins.model.*
import hudson.security.*

println "--> Skipping setup wizard and creating local admin user"

System.setProperty("jenkins.install.runSetupWizard", "false")

def instance = Jenkins.getInstance()

def hudsonRealm = new HudsonPrivateSecurityRealm(false)
hudsonRealm.createAccount("admin", "admin")
instance.setSecurityRealm(hudsonRealm)

def strategy = new FullControlOnceLoggedInAuthorizationStrategy()
strategy.setAllowAnonymousRead(false)
instance.setAuthorizationStrategy(strategy)

instance.save()
