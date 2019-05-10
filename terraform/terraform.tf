terraform {
    backend "s3" {
        bucket = "gustavo-terraform-state"
        key = "dorapos"
        region = "us-east-1"
        profile = "terraform"
    }
}
