Feature: Switch Account user

Scenario: addaccount
	Given I access resource accounturl url "/users/add"
	When i send the addAccountRequst json data
		           """
					{
					"channelId": "2",
					"channelName":"INTERNET",
					"delegationAlias":"loll",
					"delegationTypeId":"1",
					"verficationkey":"pZvoLtyUTstCNafW",
					"delegationMsisdn":"01061728280",
					"msisdn":"01061728289"
					}
   					 """
	Then the response code for send addAccountRequst service should be 0 in response Body