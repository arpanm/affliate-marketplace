import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './sponsor.reducer';

export const SponsorDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const sponsorEntity = useAppSelector(state => state.sponsor.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="sponsorDetailsHeading">
          <Translate contentKey="affliateMarketplaceApp.sponsor.detail.title">Sponsor</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{sponsorEntity.id}</dd>
          <dt>
            <span id="sponsorName">
              <Translate contentKey="affliateMarketplaceApp.sponsor.sponsorName">Sponsor Name</Translate>
            </span>
          </dt>
          <dd>{sponsorEntity.sponsorName}</dd>
          <dt>
            <span id="sponsorDescription">
              <Translate contentKey="affliateMarketplaceApp.sponsor.sponsorDescription">Sponsor Description</Translate>
            </span>
          </dt>
          <dd>{sponsorEntity.sponsorDescription}</dd>
          <dt>
            <span id="sponsorBanner1Url">
              <Translate contentKey="affliateMarketplaceApp.sponsor.sponsorBanner1Url">Sponsor Banner 1 Url</Translate>
            </span>
          </dt>
          <dd>{sponsorEntity.sponsorBanner1Url}</dd>
          <dt>
            <span id="sponsorBanner2Url">
              <Translate contentKey="affliateMarketplaceApp.sponsor.sponsorBanner2Url">Sponsor Banner 2 Url</Translate>
            </span>
          </dt>
          <dd>{sponsorEntity.sponsorBanner2Url}</dd>
          <dt>
            <span id="sponsorBanner3Url">
              <Translate contentKey="affliateMarketplaceApp.sponsor.sponsorBanner3Url">Sponsor Banner 3 Url</Translate>
            </span>
          </dt>
          <dd>{sponsorEntity.sponsorBanner3Url}</dd>
          <dt>
            <span id="sponsorExternalUrl">
              <Translate contentKey="affliateMarketplaceApp.sponsor.sponsorExternalUrl">Sponsor External Url</Translate>
            </span>
          </dt>
          <dd>{sponsorEntity.sponsorExternalUrl}</dd>
          <dt>
            <span id="sponsorLogoUrl">
              <Translate contentKey="affliateMarketplaceApp.sponsor.sponsorLogoUrl">Sponsor Logo Url</Translate>
            </span>
          </dt>
          <dd>{sponsorEntity.sponsorLogoUrl}</dd>
          <dt>
            <span id="isActive">
              <Translate contentKey="affliateMarketplaceApp.sponsor.isActive">Is Active</Translate>
            </span>
          </dt>
          <dd>{sponsorEntity.isActive ? 'true' : 'false'}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="affliateMarketplaceApp.sponsor.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{sponsorEntity.createdBy}</dd>
          <dt>
            <span id="createdOn">
              <Translate contentKey="affliateMarketplaceApp.sponsor.createdOn">Created On</Translate>
            </span>
          </dt>
          <dd>
            {sponsorEntity.createdOn ? <TextFormat value={sponsorEntity.createdOn} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="affliateMarketplaceApp.sponsor.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{sponsorEntity.updatedBy}</dd>
          <dt>
            <span id="updatedOn">
              <Translate contentKey="affliateMarketplaceApp.sponsor.updatedOn">Updated On</Translate>
            </span>
          </dt>
          <dd>
            {sponsorEntity.updatedOn ? <TextFormat value={sponsorEntity.updatedOn} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
        </dl>
        <Button tag={Link} to="/sponsor" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/sponsor/${sponsorEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default SponsorDetail;
