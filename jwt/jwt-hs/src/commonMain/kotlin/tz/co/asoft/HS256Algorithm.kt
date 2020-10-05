package tz.co.asoft

class HS256Algorithm(secret: String) : JWTAlgorithm("HS256", HS256Signer(secret), HS256Verifier(secret))