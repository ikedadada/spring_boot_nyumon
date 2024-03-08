:imagesdir: images

== ハンズオン
=== 概要
Spring Boot のオートコンフィグレーションを使うことで、コンフィグレーションの手間が大きく減ることを確認します。

=== 手順
. pom.xml の中身をのぞいてみましょう。<dependencies>タグの中に、「spring-boot-starter-jdbc」というアーティファクトが指定されています。このアーティファクトを指定するだけで、Spring のライブラリや、データベースアクセス、トランザクション制御のためのライブラリが利用できるようになります。

. TrainingApplication クラスの中身をのぞいてみましょう。Spring Boot のオートコンフィグレーションを使用していないため、DataSource、JdbcTemplate、PlatformTransactionManager の Bean 定義を行っています。

. TrainingApplication クラスを、オートコンフィグレーションを使用する形に修正しましょう。DI コンテナの生成は、AnnotationConfigApplicationContext クラスを使用するのではなく、SpringApplication クラスの run メソッドを使用します。DataSource、JdbcTemplate、TransactionManager の Bean 定義は不要になるので削除しましょう。Spring Boot は、クラスパス直下の「schema.sql」「data.sql」ファイルを自動的に読込みますので、明示的な指定は不要です。

. TrainingApplication クラスを実行します。上手く動けば、コンソール画面にトランザクション制御しているログが出力されます。

. src/main/resources フォルダの下に application.properties ファイルを作成して、以下の記述をしてみましょう。

- ***
  ## logging.level.org.springframework.jdbc.core.JdbcTemplate=DEBUG
- もう一度 TrainingApplication クラスを実行すると、実行された SQL がコンソールに出力されます。Spring Boot がログ回りの設定をカスタマイズし、JdbcTemplate クラスのロガーのログレベルを変更できたことが分かります。
  なお、ロガーの名前は以下の赤枠の部分で確認できます。

image::log-sample.png[]
