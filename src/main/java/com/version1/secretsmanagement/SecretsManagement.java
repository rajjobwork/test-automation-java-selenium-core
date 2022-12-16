package com.version1.secretsmanagement;

import com.azure.core.util.polling.SyncPoller;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.azure.security.keyvault.secrets.models.DeletedSecret;
import com.azure.security.keyvault.secrets.models.KeyVaultSecret;

public class SecretsManagement {
	public static SecretClient secretBuilder(String keyVaultName) throws InterruptedException, IllegalArgumentException {
	   String keyVaultUri = "https://" + keyVaultName + ".vault.azure.net";
       SecretClient secretClient = new SecretClientBuilder()
           .vaultUrl(keyVaultUri)
           .credential(new DefaultAzureCredentialBuilder().build())
           .buildClient();
       return secretClient;
	}
	
	public static void setSecret(String keyVaultName,String secretName,String secretValue) throws InterruptedException, IllegalArgumentException {
	   secretBuilder(keyVaultName).setSecret(new KeyVaultSecret(secretName, secretValue));
	    }
	
	public static String getSecret(String keyVaultName,String secretName) throws InterruptedException, IllegalArgumentException {
       KeyVaultSecret retrievedSecret = secretBuilder(keyVaultName).getSecret(secretName);
	   return retrievedSecret.getValue();
    }
	
	public static void deleteSecret(String keyVaultName,String secretName) throws InterruptedException, IllegalArgumentException {
       SyncPoller<DeletedSecret, Void> deletionPoller = secretBuilder(keyVaultName).beginDeleteSecret(secretName);
       deletionPoller.waitForCompletion();
    }
}
