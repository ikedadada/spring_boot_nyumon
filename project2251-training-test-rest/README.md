== ハンズオン
=== 概要
REST API を提供するテストクラスを作成します。Controller のユニットテスト、およびインテグレーションテストを作成します。

=== 手順
. TrainingAdminRestControllerTest クラスを実行しましょう。テストがパスするはずです。何をテストしているか分かりますか？

. TrainingAdminRestController クラスの getTrainings(1 件ではなく、複数件のデータを取得)メソッドをテストするテストメソッドを作成しましょう。

. TrainingAdminRestControllerIntegrationTestMockMvc テストを実行しましょう。テストがパスするはずです。テストメソッドを追加して、ID を指定して研修データ 1 件を取得する API をテストしましょう。

. TrainingAdminRestControllerIntegrationTestApServer テストを実行しましょう。テストがパスするはずです。テストメソッドを追加して、ID を指定して研修データ 1 件を取得する API をテストしましょう。

. それぞれのテストクラスは、Controller の処理を起点にしてテストしていますが、どのような違いがありますか？
