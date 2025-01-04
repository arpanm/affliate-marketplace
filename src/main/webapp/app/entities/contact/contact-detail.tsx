import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './contact.reducer';

export const ContactDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const contactEntity = useAppSelector(state => state.contact.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="contactDetailsHeading">
          <Translate contentKey="affliateMarketplaceApp.contact.detail.title">Contact</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{contactEntity.id}</dd>
          <dt>
            <span id="message">
              <Translate contentKey="affliateMarketplaceApp.contact.message">Message</Translate>
            </span>
          </dt>
          <dd>{contactEntity.message}</dd>
          <dt>
            <span id="isContactViewed">
              <Translate contentKey="affliateMarketplaceApp.contact.isContactViewed">Is Contact Viewed</Translate>
            </span>
          </dt>
          <dd>{contactEntity.isContactViewed ? 'true' : 'false'}</dd>
          <dt>
            <span id="isFollowed">
              <Translate contentKey="affliateMarketplaceApp.contact.isFollowed">Is Followed</Translate>
            </span>
          </dt>
          <dd>{contactEntity.isFollowed ? 'true' : 'false'}</dd>
          <dt>
            <span id="isDeleted">
              <Translate contentKey="affliateMarketplaceApp.contact.isDeleted">Is Deleted</Translate>
            </span>
          </dt>
          <dd>{contactEntity.isDeleted ? 'true' : 'false'}</dd>
          <dt>
            <span id="isActive">
              <Translate contentKey="affliateMarketplaceApp.contact.isActive">Is Active</Translate>
            </span>
          </dt>
          <dd>{contactEntity.isActive ? 'true' : 'false'}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="affliateMarketplaceApp.contact.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{contactEntity.createdBy}</dd>
          <dt>
            <span id="createdOn">
              <Translate contentKey="affliateMarketplaceApp.contact.createdOn">Created On</Translate>
            </span>
          </dt>
          <dd>
            {contactEntity.createdOn ? <TextFormat value={contactEntity.createdOn} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="affliateMarketplaceApp.contact.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{contactEntity.updatedBy}</dd>
          <dt>
            <span id="updatedOn">
              <Translate contentKey="affliateMarketplaceApp.contact.updatedOn">Updated On</Translate>
            </span>
          </dt>
          <dd>
            {contactEntity.updatedOn ? <TextFormat value={contactEntity.updatedOn} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <Translate contentKey="affliateMarketplaceApp.contact.sender">Sender</Translate>
          </dt>
          <dd>{contactEntity.sender ? contactEntity.sender.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/contact" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/contact/${contactEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ContactDetail;
