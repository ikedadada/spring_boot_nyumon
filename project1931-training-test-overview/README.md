== ハンズオン
=== 概要
Spring のテストサポート機能を使用したテストクラスを実行してみます。

=== 手順
. src/test/java 配下の TrainingServiceTest.java の内容を確認し、実行してみてください。以下の問いかけに答えることができますか？(※データベースのデータは、Spring Boot により「0001-training-common」プロジェクトの src/main/resources/data.sql を使用して登録されます)
.. @SpringBootTest を付けることで、何が起こるのでしょうか？
.. @Autowired は、何をしているのでしょうか？
.. DI コンテナに読み込まれる JavaConfig クラスはどれでしょうか？

. テスト実行時にだけ、トランザクション制御のログをコンソールに出力するようにしてください。トランザクション制御のログに出力するためには、Spring Boot が読み込むプロパティファイルに以下を記述します。

- ***
  ## logging.level.org.springframework.jdbc.support.JdbcTransactionManager=DEBUG
