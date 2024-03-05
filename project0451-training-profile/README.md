== ハンズオン

=== 概要
プロファイルを使ってコンフィグレーションを切り替えます。

=== 手順
. TrainingApplication クラスの main メソッドを実行してみましょう。「expected single matching bean but found 2」というエラーメッセージが表示されます。これは、TrainingServiceImpl オブジェクトがインジェクションしようとしている TrainingRepository 型のオブジェクトが、DI コンテナに 2 つ存在するからです(JdbcTrainingRepository オブジェクトと ExternalTrainingRepository オブジェクト)。

. JdbcTrainingRepository クラスと ExternalTrainingRepository クラスを、別々のプロファイルでグルーピングしましょう。プロファイル名は任意です。

. プロファイルでグルーピングしたら、いずれかのプロファイル名を有効にします。有効なプロファイルを指定するには、DI コンテナの生成の前に、以下の処理を加えます。

- ***
  ## System.setProperty("spring.profiles.active", "プロファイル名");
- 「System.setProperty(...」は、Java のシステムプロパティを指定する記述です。本来は Java コマンドのオプションでシステムプロパティを指定したほうがよいですが、今回は簡単のため、プログラムの中で指定する形にします。
  指定したプロファイル名に応じて、JdbcTrainingRepository オブジェクトと ExternalTrainingRepository オブジェクトの、いずれかが使用されて動作するはずです。
