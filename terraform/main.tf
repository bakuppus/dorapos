provider "aws" {
    version = "~> 2.9"
    shared_credentials_file = "~/.aws/credentials"
    profile = "terraform"
}
