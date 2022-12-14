# A Crypto Wallet

## Set Up and Run
- Install IntelliJ IDEA, Maven (version 4.0), and Java (16)
- Install dependencies using IntelliJ and Maven
- Run the main method in the class of 'src/main/java/MainFrame.java'
- Make sure internet is connected (Note: All blockchain operations make calls to the test net of Binance chain)

## Features
### Change Account
The wallet has a default account. 
You can change the account by clicking the button 'Change Account' on the top right corner.
Your can see your changed account address on the top left corner.

### Update Balance
You can update your balance by clicking the button 'Update'.
Updating process will take a few seconds to call data from blockchain, but it will not affect your other operations due to multi-threading design.

### Add Coin
You can add a coin to your wallet by clicking the button 'Add'.
For most coin, you can add them by entering their smart contract addresses.
For BNB which is the gas of the chain, you can only add it by entering 'eth' which is a traditional symbol 'ether' on this chain.
The new coin will be shown on the wallet and stored into SQLite database.

### Delete Coin
You can delete a coin from your wallet by selecting a coin and clicking the button 'Delete'.
Deleting process will swap the last row to replace the selected row.
The selected coin will be removed from the wallet and SQLite database.

### Send Coin
You can send a coin to another account by selecting a coin and clicking the button 'Send'.
You will be asked to enter the receiver's address and the amount of the coin you want to send.
Sending process will take about 15 seconds to interact with blockchain, but it will not affect your other operations due to multi-threading design.
The sending result will be shown on the tab of 'Transaction' and stored into SQLite database.

### Transaction History
You can see all transaction history on the tab of 'Transaction'.
Using the Hash of a transaction, you can see on-chain data at 'https://testnet.bscscan.com/'.

