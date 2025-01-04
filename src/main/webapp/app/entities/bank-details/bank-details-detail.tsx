import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './bank-details.reducer';

export const BankDetailsDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const bankDetailsEntity = useAppSelector(state => state.bankDetails.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="bankDetailsDetailsHeading">
          <Translate contentKey="affliateMarketplaceApp.bankDetails.detail.title">BankDetails</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{bankDetailsEntity.id}</dd>
          <dt>
            <span id="accountName">
              <Translate contentKey="affliateMarketplaceApp.bankDetails.accountName">Account Name</Translate>
            </span>
          </dt>
          <dd>{bankDetailsEntity.accountName}</dd>
          <dt>
            <span id="accountNo">
              <Translate contentKey="affliateMarketplaceApp.bankDetails.accountNo">Account No</Translate>
            </span>
          </dt>
          <dd>{bankDetailsEntity.accountNo}</dd>
          <dt>
            <span id="bankName">
              <Translate contentKey="affliateMarketplaceApp.bankDetails.bankName">Bank Name</Translate>
            </span>
          </dt>
          <dd>{bankDetailsEntity.bankName}</dd>
          <dt>
            <span id="ifsc">
              <Translate contentKey="affliateMarketplaceApp.bankDetails.ifsc">Ifsc</Translate>
            </span>
          </dt>
          <dd>{bankDetailsEntity.ifsc}</dd>
          <dt>
            <span id="proofUrl">
              <Translate contentKey="affliateMarketplaceApp.bankDetails.proofUrl">Proof Url</Translate>
            </span>
          </dt>
          <dd>{bankDetailsEntity.proofUrl}</dd>
          <dt>
            <span id="isActive">
              <Translate contentKey="affliateMarketplaceApp.bankDetails.isActive">Is Active</Translate>
            </span>
          </dt>
          <dd>{bankDetailsEntity.isActive ? 'true' : 'false'}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="affliateMarketplaceApp.bankDetails.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{bankDetailsEntity.createdBy}</dd>
          <dt>
            <span id="createdOn">
              <Translate contentKey="affliateMarketplaceApp.bankDetails.createdOn">Created On</Translate>
            </span>
          </dt>
          <dd>
            {bankDetailsEntity.createdOn ? (
              <TextFormat value={bankDetailsEntity.createdOn} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="affliateMarketplaceApp.bankDetails.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{bankDetailsEntity.updatedBy}</dd>
          <dt>
            <span id="updatedOn">
              <Translate contentKey="affliateMarketplaceApp.bankDetails.updatedOn">Updated On</Translate>
            </span>
          </dt>
          <dd>
            {bankDetailsEntity.updatedOn ? (
              <TextFormat value={bankDetailsEntity.updatedOn} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
        </dl>
        <Button tag={Link} to="/bank-details" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/bank-details/${bankDetailsEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default BankDetailsDetail;
