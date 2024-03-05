== ハンズオン
=== 概要
DI を適用しているプログラムがどのようなものかを確認します。
DI を適用することで、依存オブジェクトの具象クラスを効率的に切り替えれることを確認します。

=== 手順
. TrainingApplication クラスの main メソッドを実行してみましょう。Repository の具象クラスは、どれが使用されたでしょうか？

. TrainingServiceImplTest(src/test/java 配下)クラスのテストを実行してみましょう。TrainingServiceImpl オブジェクトが用意できてないため、NullPointerException が発生します。

. TrainingServiceImplTest クラスのテストメソッドの中で TrainingServiceImpl オブジェクトを生成しましょう。TrainingServiceImpl オブジェクトにインジェクションする依存オブジェクトの具象クラスは、MockTrainingRepository クラスを使用してください。TrainingServiceImpl オブジェクトが生成できたら、再度 TrainingServiceImplTest のテストを実行します。テストがパスするはずです。
