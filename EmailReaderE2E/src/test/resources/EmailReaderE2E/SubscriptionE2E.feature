#Author: khanh.tx@live.com

@E2E
Feature: Subscription

  Scenario: Subscription with valid account and information on FrontEnd module
    Given I am staying at Testmaster homepage
    When I provide the valid email and it isn't to be used before
    And I do subscription to show extra form to provide more personal information
    And I provide required valid information on extra form
    And I wait until Activation Email was sent to my MailBox then open Activation Link
    Then I should see the message "Bạn đã đăng ký nhận tin thành công"
    
