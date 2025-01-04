import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './competition-payment-to-winner.reducer';

export const CompetitionPaymentToWinnerDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const competitionPaymentToWinnerEntity = useAppSelector(state => state.competitionPaymentToWinner.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="competitionPaymentToWinnerDetailsHeading">
          <Translate contentKey="affliateMarketplaceApp.competitionPaymentToWinner.detail.title">CompetitionPaymentToWinner</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{competitionPaymentToWinnerEntity.id}</dd>
          <dt>
            <span id="prizeTitle">
              <Translate contentKey="affliateMarketplaceApp.competitionPaymentToWinner.prizeTitle">Prize Title</Translate>
            </span>
          </dt>
          <dd>{competitionPaymentToWinnerEntity.prizeTitle}</dd>
          <dt>
            <span id="prizeAmount">
              <Translate contentKey="affliateMarketplaceApp.competitionPaymentToWinner.prizeAmount">Prize Amount</Translate>
            </span>
          </dt>
          <dd>{competitionPaymentToWinnerEntity.prizeAmount}</dd>
          <dt>
            <span id="tdsAmount">
              <Translate contentKey="affliateMarketplaceApp.competitionPaymentToWinner.tdsAmount">Tds Amount</Translate>
            </span>
          </dt>
          <dd>{competitionPaymentToWinnerEntity.tdsAmount}</dd>
          <dt>
            <span id="tdsCertificateUrl">
              <Translate contentKey="affliateMarketplaceApp.competitionPaymentToWinner.tdsCertificateUrl">Tds Certificate Url</Translate>
            </span>
          </dt>
          <dd>{competitionPaymentToWinnerEntity.tdsCertificateUrl}</dd>
          <dt>
            <span id="otherDeductionAmount">
              <Translate contentKey="affliateMarketplaceApp.competitionPaymentToWinner.otherDeductionAmount">
                Other Deduction Amount
              </Translate>
            </span>
          </dt>
          <dd>{competitionPaymentToWinnerEntity.otherDeductionAmount}</dd>
          <dt>
            <span id="deductionReason">
              <Translate contentKey="affliateMarketplaceApp.competitionPaymentToWinner.deductionReason">Deduction Reason</Translate>
            </span>
          </dt>
          <dd>{competitionPaymentToWinnerEntity.deductionReason}</dd>
          <dt>
            <span id="deductionJsonData">
              <Translate contentKey="affliateMarketplaceApp.competitionPaymentToWinner.deductionJsonData">Deduction Json Data</Translate>
            </span>
          </dt>
          <dd>{competitionPaymentToWinnerEntity.deductionJsonData}</dd>
          <dt>
            <span id="deductionCertificateUrl">
              <Translate contentKey="affliateMarketplaceApp.competitionPaymentToWinner.deductionCertificateUrl">
                Deduction Certificate Url
              </Translate>
            </span>
          </dt>
          <dd>{competitionPaymentToWinnerEntity.deductionCertificateUrl}</dd>
          <dt>
            <span id="paidAmount">
              <Translate contentKey="affliateMarketplaceApp.competitionPaymentToWinner.paidAmount">Paid Amount</Translate>
            </span>
          </dt>
          <dd>{competitionPaymentToWinnerEntity.paidAmount}</dd>
          <dt>
            <span id="transactionId">
              <Translate contentKey="affliateMarketplaceApp.competitionPaymentToWinner.transactionId">Transaction Id</Translate>
            </span>
          </dt>
          <dd>{competitionPaymentToWinnerEntity.transactionId}</dd>
          <dt>
            <span id="transactionScreenshotUrl">
              <Translate contentKey="affliateMarketplaceApp.competitionPaymentToWinner.transactionScreenshotUrl">
                Transaction Screenshot Url
              </Translate>
            </span>
          </dt>
          <dd>{competitionPaymentToWinnerEntity.transactionScreenshotUrl}</dd>
          <dt>
            <span id="transactionDate">
              <Translate contentKey="affliateMarketplaceApp.competitionPaymentToWinner.transactionDate">Transaction Date</Translate>
            </span>
          </dt>
          <dd>
            {competitionPaymentToWinnerEntity.transactionDate ? (
              <TextFormat value={competitionPaymentToWinnerEntity.transactionDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="transactionStatus">
              <Translate contentKey="affliateMarketplaceApp.competitionPaymentToWinner.transactionStatus">Transaction Status</Translate>
            </span>
          </dt>
          <dd>{competitionPaymentToWinnerEntity.transactionStatus}</dd>
          <dt>
            <span id="paidBy">
              <Translate contentKey="affliateMarketplaceApp.competitionPaymentToWinner.paidBy">Paid By</Translate>
            </span>
          </dt>
          <dd>{competitionPaymentToWinnerEntity.paidBy}</dd>
          <dt>
            <span id="isActive">
              <Translate contentKey="affliateMarketplaceApp.competitionPaymentToWinner.isActive">Is Active</Translate>
            </span>
          </dt>
          <dd>{competitionPaymentToWinnerEntity.isActive ? 'true' : 'false'}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="affliateMarketplaceApp.competitionPaymentToWinner.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{competitionPaymentToWinnerEntity.createdBy}</dd>
          <dt>
            <span id="createdOn">
              <Translate contentKey="affliateMarketplaceApp.competitionPaymentToWinner.createdOn">Created On</Translate>
            </span>
          </dt>
          <dd>
            {competitionPaymentToWinnerEntity.createdOn ? (
              <TextFormat value={competitionPaymentToWinnerEntity.createdOn} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="affliateMarketplaceApp.competitionPaymentToWinner.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{competitionPaymentToWinnerEntity.updatedBy}</dd>
          <dt>
            <span id="updatedOn">
              <Translate contentKey="affliateMarketplaceApp.competitionPaymentToWinner.updatedOn">Updated On</Translate>
            </span>
          </dt>
          <dd>
            {competitionPaymentToWinnerEntity.updatedOn ? (
              <TextFormat value={competitionPaymentToWinnerEntity.updatedOn} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
        </dl>
        <Button tag={Link} to="/competition-payment-to-winner" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/competition-payment-to-winner/${competitionPaymentToWinnerEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default CompetitionPaymentToWinnerDetail;
